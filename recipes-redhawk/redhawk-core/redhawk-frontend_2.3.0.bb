DESCRIPTION = "REDHAWK Framework FrontEnd Interfaces"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"

RDEPENDS_${PN}-python = "redhawk-bulkio-python"

SRC_URI = "git://github.com/RedhawkSDR/frontendInterfaces.git;branch=master;protocol=git \
file://03_Add_Missing_Files.patch \
file://05_Remove_Custom_Ossie_Check_Frontend.patch \
"
SRCREV = "2.3.0"

PR = "r0" 

S = "${WORKDIR}/git"


# Set install location OSSIEHOME and SDRROOT
OSSIEHOME = "/usr/lib/redhawk/core"
SDRROOT = "/usr/lib/redhawk/sdr"

export PKG_CONFIG_PATH:="${STAGING_DIR_TARGET}/${OSSIEHOME}/lib/pkgconfig"

PACKAGES += "${PN}-python"

FILES_${PN}-python += " \
${OSSIEHOME}/lib/python \
"

FILES_${PN} += " \
${OSSIEHOME}/lib/lib* \
"

FILES_${PN}-dbg += " \
${OSSIEHOME}/lib/.debug \
${OSSIEHOME}/lib/.debug/* \
"

FILES_${PN}-dev += " \
${OSSIEHOME}/lib/*.so \
${OSSIEHOME}/include \
${OSSIEHOME}/lib/pkgconfig \
"

FILES_${PN}-staticdev += " \
${OSSIEHOME}/lib/*.a \
"


# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the sasme as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative

EXTRA_OECONF += "--with-ossie=${OSSIEHOME} --with-boost=${STAGING_DIR_TARGET}/usr --with-boost-thread=boost_thread --disable-java --disable-log4cxx --libdir=${OSSIEHOME}/lib --includedir=${OSSIEHOME}/include"

# Since prefix is set this has to override that
CACHED_CONFIGUREVARS += "ac_cv_pymod_ossie=yes ac_cv_pymod_bulkio_bulkioInterfaces=yes"

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

