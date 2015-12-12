DESCRIPTION = "REDHAWK Core Framework GPP"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS_${PN} = "redhawk-TuneFilterDecimate redhawk-fastfilter redhawk-RBDSDecoder redhawk-AmFmPmBasebandDemod redhawk-psksoft"
RDEPENDS_${PN} = "redhawk-TuneFilterDecimate redhawk-fastfilter redhawk-RBDSDecoder redhawk-AmFmPmBasebandDemod redhawk-psksoft"

SRC_URI = "git://github.com/RedhawkSDR/FM_RBDS_demo.git;branch=master;protocol=git \
"

SRCREV = "1.0.0"

PR = "r0" 

S = "${WORKDIR}/git/"

inherit redhawk-waveform

