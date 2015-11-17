DESCRIPTION = "Configures a node using the nodeconfig script at startup"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "LGPL-3.0"
DEPENDS += "redhawk-gpp"
PR = "r0"

SRC_URI = "\
file://gppnode \
file://COPYING \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "gppnode"
INITSCRIPT_PARAMS = "defaults 99"

do_install () {
        install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d 
        install -m 0755 ${WORKDIR}/gppnode ${D}${sysconfdir}/init.d/gppnode
}

