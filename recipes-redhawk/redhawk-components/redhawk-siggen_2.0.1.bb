DESCRIPTION = "REDHAWK Core Framework GPP"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"

SRC_URI = "git://github.com/RedhawkSDR/SigGen.git;branch=master;protocol=git \
"

# Build the 2.0 release
SRCREV = "2.0.1"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

# Set install location OSSIEHOME and SDRROOT
OSSIEHOME = "/usr/lib/redhawk/core"
SDRROOT = "/usr/lib/redhawk/sdr"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/SigGen/cpp/.debug/SigGen"

inherit redhawk-component redhawk-override-proc

