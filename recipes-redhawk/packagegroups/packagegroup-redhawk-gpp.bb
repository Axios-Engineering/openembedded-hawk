SUMMARY = "A REDHAWK GPP that starts up automatically on boot"
LICENSE = "LGPL3"
PR = "r1"

inherit packagegroup

PACKAGES = "${PN}"

SUMMARY_${PN} = "REDHAWK GPP"

RDEPENDS_${PN} = "\
    redhawk-core \
    redhawk-bulkio \
    redhawk-frontend \
    redhawk-gpp \
    "
