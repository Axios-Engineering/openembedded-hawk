DESCRIPTION = "REDHAWK Device for the RTL2832U"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=92aadbd9e4b26926809a4e97460613d5"

DEPENDS = "redhawk-frontend redhawk-bulkio rtlsdr"
RDEPENDS_${PN} = "redhawk-frontend rtlsdr"

SRC_URI = "git://github.com/RedhawkSDR/RTL2832U.git;branch=master;protocol=git \
file://01_Fix_rtl_version_constraint.patch \ 
file://02_remove_arm_impl.patch;patchdir=${S}/../;striplevel=0 \
"

SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dev/devices/RTL2832U/cpp/.debug/RTL2832U"

inherit redhawk-device redhawk-override-proc
