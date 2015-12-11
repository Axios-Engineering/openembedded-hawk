DESCRIPTION = "AMFMPM Demod"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio redhawk-dsp"
RDEPENDS_${PN} = "redhawk-bulkio redhawk-dsp"
SRC_URI = "git://github.com/RedhawkSDR/AmFmPmBasebandDemod.git;branch=master;protocol=git \
file://01_AmFmPmBasebandDemod_fix_dep_checks.patch \
"

# Track the latest
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/AmFmPmBasebandDemod/cpp/.debug/AmFmPmBasebandDemod"

inherit redhawk-component

export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${SDRROOT}/dom/deps/rh/dsp/cpp/lib/pkgconfig"

