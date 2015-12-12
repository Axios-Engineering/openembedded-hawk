DESCRIPTION = "REDHAWK Core Framework"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "omnievents omniorbpy-native log4cxx omniorb e2fsprogs apr-util apr zip expat boost boost-native"
RDEPENDS_${PN} = "omnievents log4cxx omniorb e2fsprogs apr-util apr zip expat boost"
RDEPENDS_${PN}-python = "${PN} omniorbpy python-numpy python-threading python-numbers python-resource"

SRC_URI = "git://github.com/redhawksdr/framework-core.git;branch=master;protocol=git \
file://00_core_add_xsd_files.patch \
file://01_core_OSSIEHOME_prefix.patch \
file://02_core_Remove_Tests.patch \
file://04_core_Remove_x86_DomMgr.patch \
file://06_core_Fix_Idl_prefix.patch \
file://07_core_arm_based_device_manager.patch \
file://08_core_remove_csh_scripts.patch \
file://09_core_allow_idl_dir_set.patch \
file://10_core_g++4.9-fix.patch \
file://11_core_fix_sharedlib_prefix.patch \
"

SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/src"

PACKAGES += "${PN}-python"
PROVIDES += "${PN}-python"

FILES_${PN}-python += " \
${OSSIEHOME}/lib/python \
${OSSIEHOME}/bin/sdrlint \
${OSSIEHOME}/bin/prf2py.py \
${OSSIEHOME}/bin/cleanns \
${OSSIEHOME}/bin/redhawk-softpkg \
${OSSIEHOME}/bin/rh_net_diag \
${OSSIEHOME}/bin/rhlauncher \
${OSSIEHOME}/bin/nodeCleanup.py \
${OSSIEHOME}/bin/cleanomni \
${OSSIEHOME}/bin/scaclt \
${OSSIEHOME}/bin/qtbrowse \
${OSSIEHOME}/bin/py2prf \
${OSSIEHOME}/bin/eventviewer \
${OSSIEHOME}/bin/cleanes \
"

FILES_${PN} += " \
${OSSIEHOME}/bin/nodeBooter \
${OSSIEHOME}/etc \
${OSSIEHOME}/lib/lib* \
${SDRROOT}/* \
"

FILES_${PN}-dbg += " \
${SDRROOT}/dev/mgr/.debug \
${SDRROOT}/dev/mgr/.debug/* \
${SDRROOT}/dom/mgr/.debug \
${SDRROOT}/dom/mgr/.debug/* \
${OSSIEHOME}/lib/.debug/* \
${OSSIEHOME}/bin/.debug/* \
"

FILES_${PN}-staticdev += " \
${OSSIEHOME}/lib/*.a \
"

FILES_${PN}-dev += " \
${OSSIEHOME}/lib/*.so \
${OSSIEHOME}/share \
${OSSIEHOME}/include \
${OSSIEHOME}/share/* \
${OSSIEHOME}/lib/pkgconfig \
"

inherit redhawk-core

