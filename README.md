
OpenEmbedded-Hawk
=================

What is Yocto/Open-Embedded?
---------------------------
From the [Yocto][1] page itself:


>It's a complete embedded Linux development environment with tools, metadata, and documentation - everything you need. The free tools are easy to get started with, powerful to work with (including emulation environments, debuggers, an Application Toolkit Generator, etc.) and they allow projects to be carried forward over time without causing you to lose optimizations and investments made during the project’s prototype phase.

In short, [Yocto][1] allows you to build a custom, light weight embedded linux distribution  built specifically for your hardware.

What is Openembedded-Hawk
--------------------------
Openembedded-hawk is a set of [Yocto][1]/[Open-Embedded][2] recipes for the [REDHAWK][3] framework, its dependencies, and a handful of example components, devices, and shared libraries.

This repository, along with the base Yocto framework will enable you to build the REDHAWK framework for any hardware platform in which a Board Support Package is available.

Is my hardware supported?
-------------------------
The Yocto website provides a list of [Official BSPs][4] which include common hardware platforms like the Raspberry Pi, BeagleBoard, BeagleBone, NUC, Intel Atom, etc.

There are plenty of BSPs floating around for other hardware platforms so do some searching before you write your own.

Getting Started
----------------

1. Install the required software listed in the [Yocto Quick Start Packages Section][6]. If you are running on a machine that does not have python 2.7 (ex. CentOS6) see the section on running with Docker. 

1. Checkout the Yocto Poky repositories dizzy branch:

    git clone -b dizzy git://git.yoctoproject.org/poky.git


1. Clone the openembedded-hawk repository inside the poky folder as meta-redhawk.

    ```
    cd poky
    git clone https://github.com/Axios-Engineering/openembedded-hawk.git meta-redhawk
    ```
1. Optionally clone meta-openembedded for additional dependencies (like fftw)

    ```
    git clone -b dizzy git://git.openembedded.org/meta-openembedded
    ```
1. Source the build-env script

    ```
    source oe-init-build-env
    ```

1. Pick a machine type within the conf/local.conf file. The default will build for an emulated x86 machine type.

1. Add REDHAWK recipes or packagegroups to the build image. The easiest way to do this is by using the conf/local.conf file and adding the IMAGE_INSTALL_append variable at the end. Here is an example that adds everything needed for a GPP node that will autostart on boot. Note that all the dependencies will automatically be built including redhawk-core. See the next section for a full list of options. 

    ```
    IMAGE_INSTALL_append = " packagegroup-redhawk-gpp "
    ```

1. Add the meta-redhawk directory to the BBLAYERS variable in conf/bblayers.conf so yocto knows where to search for our custom recipes as well as meta-openembedded/meta-oe if you chose to optionally clone that as well.

1. Build an image:
    ```
    bitbake core-image-minimal
    ```

1. If your image selected is based off of one of the qemu systems you can run it easily with the runqemu command. For example, for machine type qemuarm you simply run:
   ```
   runqemu qemuarm
   ```



Features / Options
--------------------

* Python broken out - With the introduction of the C++ based GPP in REDHAWK 2.0, there is now no real dependency on java or python. To keep file size down, the frameworks python packages have been broken out into their own packages. For instance, the redhawk-core package includes only the libraries and compiled executables needed while the redhawk-core-python package has a dependency on python and the framework python packages.

* Package Groups - Two REDHAWK package groups are available
 * packagegroup-redhawk-gpp - Includes everything needed to run a REDHAWK GPP node including init.d scripts.
 * packagegroup-redhawk-domain - Includes everything needed to run a REDHAWK Domain including init.d scripts.

* Automated spd processor name override - If the variable "REDHAWK_PROCESSOR_NAME" is set, usually in the conf/local.conf file, then an attempt will be made to override the values of the processor_name property in spd.xml's from x86_64 to the desired name.

* REDHAWK bbclasses - Bitbake classes are provided to simplify development. These classes include: redhawk-component, redhawk-device, redhawk-sharedlib, and redhawk-waveform. This allows for new REDHAWK components to be defined with very little knowledge of the required settings. For example, the build for psk_soft is simply:

```
DESCRIPTION = "Soft PSK Demod"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"
SRC_URI = "git://github.com/RedhawkSDR/psk_soft.git;branch=master;protocol=git \
"

# Build the 2.0 release
SRCREV = "2.0.0"

PR = "r0"

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/psk_soft/cpp/.debug/psk_soft"

inherit redhawk-component redhawk-override-proc
```

Building with Docker
------------------------
CentOS 6 does not meet the python version requirement for poky. It is possible to run Python 2.7 on CentOS6 with Python virtual-env or CentOS-SCL however these approaches do not work as well as the Docker approach. These section will (in the near future) detail the steps to run a build via docker.

Additional Resources
--------------------

[Yocto Mega Manual][7]

[Bitbake cheatsheet][8]

[1]: https://www.yoctoproject.org/  "Yocto Project Homepage"
[2]: http://www.openembedded.org/wiki/Main_Page  "Open-Embedded Project Homepage"
[3]: http://redhawksdr.org "REDHAWK Homepage"
[4]: https://www.yoctoproject.org/downloads/bsps?release=All&title= "Board Support Package List"
[5]: https://github.com/EttusResearch/meta-ettus "Ettus BSP"
[6]: http://www.yoctoproject.org/docs/current/yocto-project-qs/yocto-project-qs.html#packages "Required Packages"
[7]: http://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html "Yocto Mega Manual"
[8]: http://www.openembedded.org/wiki/Bitbake_cheat_sheet "Bitbake Cheat Sheet"
                                                                                                           
