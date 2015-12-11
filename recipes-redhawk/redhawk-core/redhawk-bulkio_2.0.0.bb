DESCRIPTION = "REDHAWK Framework BulkIO Interfaces"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-core"
RDEPENDS_${PN} = "redhawk-core"
RDEPENDS_${PN}-python = "redhawk-core-python"

SRC_URI = "git://github.com/RedhawkSDR/framework-bulkioInterfaces.git;branch=master;protocol=git \
file://03_Add_Missing_Files.patch \
file://05_Remove_Custom_Ossie_Check_BulkIO.patch \
"
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git"

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
${OSSIEHOME}/share/* \
${OSSIEHOME}/lib/pkgconfig \
"

FILES_${PN}-staticdev += " \
${OSSIEHOME}/lib/*.a \
"

inherit redhawk-core

