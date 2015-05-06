DESCRIPTION = "REDHAWK Framework BulkIO Interfaces"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-core"
RDEPENDS_${PN} = "redhawk-core"

SRC_URI = "git://github.com/RedhawkSDR/framework-bulkioInterfaces.git;branch=master;protocol=git \
file://03_Add_Missing_Files.patch \
file://05_Remove_Custom_Ossie_Check_BulkIO.patch \
"
SRCREV = "4ef2280307a95833fdc62f52b5f1365fc1d9f2eb"

PR = "r0" 

S = "${WORKDIR}/git"


# Set install location OSSIEHOME and SDRROOT
OSSIEHOME = "/usr/lib/redhawk/core"
SDRROOT = "/usr/lib/redhawk/sdr"

FILES_${PN} += " \
${OSSIEHOME}/* \
"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the sasme as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative

EXTRA_OECONF += "--with-ossie=${OSSIEHOME} --with-boost=${STAGING_DIR_TARGET}/usr --with-boost-system=boost_system --with-boost-thread=boost_thread --disable-java --disable-log4cxx"

# Needed so that when the python distutils is run it can get the system prefix which, since it's the build system python will be /.../x86_64-linux/usr and replace it with our host systems name.
do_configure_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

# Needed so that when the python distutils is run it can get the system prefix.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

