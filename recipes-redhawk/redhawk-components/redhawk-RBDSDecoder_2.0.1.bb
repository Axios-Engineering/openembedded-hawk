DESCRIPTION = "RBDS Decoder"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=92aadbd9e4b26926809a4e97460613d5"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"
SRC_URI = "git://github.com/RedhawkSDR/RBDSDecoder.git;branch=master;protocol=git \
file://01_RBDSDecoder_remove_custom_arm.patch;patchdir=.. \
"

# Build the 2.0 release
SRCREV = "2.0.1"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/RBDSDecoder/cpp/.debug/RBDSDecoder"
inherit redhawk-component redhawk-override-proc

