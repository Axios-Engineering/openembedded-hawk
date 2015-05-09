DESCRIPTION = "REDHAWK Core Framework"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "omniorbpy omniorbpy-native log4cxx omniorb e2fsprogs apr-util apr zip expat boost boost-native python-numpy python-threading python-numbers python-resource"
RDEPENDS_${PN} = "python omniorbpy omniorb e2fsprogs apr-util apr zip expat boost python-numpy python-threading python-subprocess python-numbers python-xml python-resource"

SRC_URI = "git://github.com/bagoulla/framework-core.git;branch=feature-no-xsd-1.10;protocol=git \
file://01_OSSIEHOME_prefix.patch \
file://02_Remove_Tests.patch \
file://03_Add_Missing_Files.patch \
file://04_Remove_x86_DomMgr.patch \
file://06_Fix_Idl_prefix.patch \
file://07_arm_based_device_manager.patch \
file://08_remove_csh_scripts.patch \
"
# I'm not sure how to set this to latest which is what I'd like.
SRCREV = "c560f59e35b7fac5780094e998c90f57350bc5a9"

PR = "r0" 

S = "${WORKDIR}/git/src"

# Set install location OSSIEHOME and SDRROOT
OSSIEHOME = "/usr/lib/redhawk/core"
SDRROOT = "/usr/lib/redhawk/sdr"

FILES_${PN} += " \
/usr/share/* \
/usr/local/* \
${OSSIEHOME}/* \
${SDRROOT}/* \
"

FILES_${PN}-dbg += " \
${SDRROOT}/dev/mgr/.debug \
${SDRROOT}/dev/mgr/.debug/* \
${SDRROOT}/dom/mgr/.debug \
${SDRROOT}/dom/mgr/.debug/* \
"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the sasme as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative 

EXTRA_OECONF += "--with-ossie=${OSSIEHOME} --with-sdr=${SDRROOT} --with-boost=${STAGING_DIR_TARGET}/usr --with-boost-system=boost_system --with-boost-filesystem=boost_filesystem --with-boost-thread=boost_thread --with-boost-regex=boost_regex --disable-java --with-expat=${STAGING_DIR_TARGET}/usr --disable-log4cxx"

# Needed so that when the python distutils is run it can get the system prefix.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

