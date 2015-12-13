DESCRIPTION = "Tunes Filters and Decimates"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=92aadbd9e4b26926809a4e97460613d5"

DEPENDS = "redhawk-bulkio redhawk-dsp redhawk-fftlib"
RDEPENDS_${PN} = "redhawk-bulkio redhawk-dsp redhawk-fftlib"
SRC_URI = "git://github.com/RedhawkSDR/TuneFilterDecimate.git;branch=master;protocol=git \
file://01_tunefilterdecimate_fix_dep_checks.patch \
"

# Track the latest
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg +="${SDRROOT}/dom/components/rh/TuneFilterDecimate/cpp/.debug/TuneFilterDecimate"

inherit redhawk-component redhawk-override-proc

export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${SDRROOT}/dom/deps/rh/dsp/cpp/lib/pkgconfig"
export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${SDRROOT}/dom/deps/rh/fftlib/cpp/lib/pkgconfig"

