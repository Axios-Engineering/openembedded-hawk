=================
openembedded-hawk
=================

A set of open embedded recipes for the redhawk core framework and its dependencies.  

This is a work in progress and needs work before it can be deployed on any embedded hardware.  Once completed, this will allow redhawk to be built and run on many of the hardware sets which are supported by the Yocto and open embedded projects.  This includes the beagleboard, pasberry pi, gumstick, and many other platforms.

=================
Getting Started
=================

1.) Install all the requird software listed here: http://openembedded.org/wiki/Getting_started

2.) Checkout the Poky repository mentioned here: https://www.yoctoproject.org/download/yocto-project-141-poky-901

3.) Clone the openembedded-hawk repository inside the poky folder.  This will create a meta-redhawk folder.

4.) From poky/meta/recipes-core/images, copy core-image-minimal.bb to core-image-redhawk.bb and add the following line below line 3:

 IMAGE_INSTALL += "omniorbpy dropbear redhawk-core redhawk-gpp redhawk-bulkio python-modules python-numpy python-threading util-linux-uuidgen"


5.) From the poky directory, "source oe-init-build-env [build-folder-name]" Where [build-folder-name] is where the build files will be kept (ex. "source oe-init-build-env mybuild")

6.) From [build-folder-name]/conf modify the bblayers.conf file and add the meta-redhawk folder which was cloned into the poky folder in step 3.

7.) Optional: Modify the local.conf file and set BB_NUMBER_TREADS and PARRALLEL_MAKE to the number of threads your processor can support.  (This is generally 2x the number of cores).  Change the MACHINE to qemuarm so that we can use the cross-compiler toolchain.

8.) From [build-folder-name] run bitbake core-image-redhawk

9.) Take a nap, this will run for a while.  Bitbake is downloading and building all the source code needed for a minimal linux distribution with redhawk.

=================
Resources
=================

Yocto Mega Manual: http://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html

Using Yocto for RaspberryPi: http://www.pimpmypi.com/blog/blogPost.php?blogPostID=7

Bitbake cheatsheet: http://www.openembedded.org/wiki/Bitbake_cheat_sheet

