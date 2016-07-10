DESCRIPTION = "REDHAWK Core Framework GPP"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-bulkio"
RDEPENDS_${PN} = "redhawk-bulkio"

SRC_URI = "git://github.com/RedhawkSDR/framework-GPP.git;branch=master;protocol=git \
file://01_gpp.patch;patchdir=.. \
file://DeviceManager.dcd.xml \
file://GPP/GPP.spd.xml \
file://GPP/GPP.scd.xml \
file://GPP/GPP.prf.xml \
"

SRCREV = "2.0.1"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

PACKAGES += "${PN}-profile"
PROVIDES += "${PN}-profile"

FILES_${PN} += "${SDRROOT}/dev/devices/GPP/*"
FILES_${PN}-dbg += "${SDRROOT}/dev/devices/GPP/cpp/.debug/GPP"

FILES_${PN}-profile += "${SDRROOT}/dev/nodes/DevMgr_GPP/DeviceManager.dcd.xml"
FILES_${PN}-profile += "${SDRROOT}/dev/nodes/DevMgr_GPP/GPP/*.xml"

inherit redhawk-device redhawk-override-proc

do_install_append() {
    install -d ${D}${SDRROOT}/dev/nodes/DevMgr_GPP/GPP
    install -m 0644 ${WORKDIR}/DeviceManager.dcd.xml ${D}${SDRROOT}/dev/nodes/DevMgr_GPP/DeviceManager.dcd.xml
    install -m 0644 ${WORKDIR}/GPP/GPP.spd.xml ${D}${SDRROOT}/dev/nodes/DevMgr_GPP/GPP/GPP.spd.xml
    install -m 0644 ${WORKDIR}/GPP/GPP.prf.xml ${D}${SDRROOT}/dev/nodes/DevMgr_GPP/GPP/GPP.prf.xml
    install -m 0644 ${WORKDIR}/GPP/GPP.scd.xml ${D}${SDRROOT}/dev/nodes/DevMgr_GPP/GPP/GPP.scd.xml
}

