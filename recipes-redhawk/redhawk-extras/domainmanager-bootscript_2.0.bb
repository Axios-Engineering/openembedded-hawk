DESCRIPTION = "Starts the domain manager on boot"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "LGPL-3.0"
DEPENDS += "redhawk-gpp"
PR = "r0"

SRC_URI = "\
file://domainManager \
file://domainManager.log4cxx \
file://COPYING \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "domainManager"
INITSCRIPT_PARAMS = "defaults 80"

SDRROOT = "/usr/lib/redhawk/sdr"

FILES_${PN} += " \
${SDRROOT}/* \
"

do_install () {
        install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d 
        install -m 0755 ${WORKDIR}/domainManager ${D}${sysconfdir}/init.d/domainManager

        install -d ${D}${SDRROOT}
        install -m 0644 ${WORKDIR}/domainManager.log4cxx ${D}${SDRROOT}/domainManager.log4cxx
}

