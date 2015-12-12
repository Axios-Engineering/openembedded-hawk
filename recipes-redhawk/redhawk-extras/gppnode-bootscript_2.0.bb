DESCRIPTION = "Configures a node using the nodeconfig script at startup"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "LGPL-3.0"
DEPENDS += "redhawk-gpp redhawk-gpp-profile"
PR = "r0"

SRC_URI = "\
file://gppnode \
file://gppnode.log4cxx \
file://COPYING \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "gppnode"
INITSCRIPT_PARAMS = "defaults 90"

SDRROOT = "/usr/lib/redhawk/sdr"

FILES_${PN} += " \
${SDRROOT}/* \
"
do_install () {
        install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d 
        install -m 0755 ${WORKDIR}/gppnode ${D}${sysconfdir}/init.d/gppnode

        install -d ${D}${SDRROOT}
        install -m 0644 ${WORKDIR}/gppnode.log4cxx ${D}${SDRROOT}/gppnode.log4cxx
}

