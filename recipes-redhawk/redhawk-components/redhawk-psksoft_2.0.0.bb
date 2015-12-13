DESCRIPTION = "Soft PSK Demod"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"
SRC_URI = "git://github.com/RedhawkSDR/psk_soft.git;branch=master;protocol=git \
"

# Track the latest
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/psk_soft/cpp/.debug/psk_soft"

inherit redhawk-component redhawk-override-proc

