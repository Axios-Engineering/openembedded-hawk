SUMMARY = "A REDHAWK Domain Controller. Nameserer and GPP nodes not included"
LICENSE = "LGPL3"
PR = "r1"

inherit packagegroup

PACKAGES = "${PN}"

SUMMARY_${PN} = "REDHAWK Domain Controller"

RDEPENDS_${PN} = "\
    redhawk-core \
    redhawk-bulkio \
    redhawk-frontend \
    "
