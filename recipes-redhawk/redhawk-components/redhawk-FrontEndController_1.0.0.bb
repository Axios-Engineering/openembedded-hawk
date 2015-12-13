DESCRIPTION = "Frontend Controller"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio redhawk-frontend"
RDEPENDS_${PN} = "redhawk-bulkio-python redhawk-frontend-python"
SRC_URI = "git://github.com/RedhawkSDR/FrontEndController.git;branch=master;protocol=git \
"

# Track the latest
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/python"

FILES_${PN} += "${SDRROOT}/*"

inherit redhawk-component redhawk-override-proc

