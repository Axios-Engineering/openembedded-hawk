DESCRIPTION = "OmniORB High Performance ORB"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=75b02c2872421380bbd47781d2bd75d3"
DEPENDS += "omniorb-native python"
RDEPENDS_${PN}-python += "python"

DEPENDS_virtclass-native += "python-native"
PR = "r2"

SRC_URI = "http://downloads.sourceforge.net/omniorb/omniORB-4.1.4.tar.gz;name=omniORB414targz \
file://omniorb_4.1.4.patch \ 
file://omniORB.cfg \
file://omniORB-cross.patch \
file://omniORB_embedded_appl.patch \
file://pyPrefixIsPrefix.patch \
file://fixPythonShebang.patch \
file://omniNames \
file://rm_LongDouble.patch \
"
SRC_URI_virtclass-native = "http://downloads.sourceforge.net/omniorb/omniORB-4.1.4.tar.gz;name=omniORB414targz \
	  file://omniorb_4.1.4.patch \
          file://omniORB.cfg \
          file://omniNames \
          file://rm_LongDouble.patch \
"

SRC_URI[omniORB414targz.md5sum] = "1f6070ff9b6339876976d61981eeaa6a"
SRC_URI[omniORB414targz.sha256sum] = "84fb9790c25d6e46248c9773747e393b429573190da2150850d4a49debda4e8e"

S = "${WORKDIR}/omniORB-${PV}"

PACKAGES += "${PN}-python"
PROVIDES += "${PN}-python"

# Here we need python libraries and the softlink for the omniidlmodule, we have to disable the check for soft links.
# Alternativly, we could packge this into the dev package and then pull that in but that would also get all the headers
# and idl files
INSANE_SKIP_${PN}-python += "dev-so"
FILES_${PN}-dev += "${datadir}/idl/omniORB/* ${datadir}/idl/omniORB/cos/*"

FILES_${PN}-python += "${libdir}/python2.7/site-packages/_omniidlmodule.so*"
FILES_${PN}-python += "${libdir}/python2.7/site-packages/omniidl/*"
FILES_${PN}-python += "${libdir}/python2.7/site-packages/omniidl_be/*"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/.debug/_omniidlmodule.so.4.1"
TARGET_CC_ARCH += "${LDFLAGS}"

inherit autotools pkgconfig pythonnative update-rc.d

INITSCRIPT_NAME = "omniNames"
INITSCRIPT_PARAMS = "defaults 10"

do_compile () {
        export TOOLBINDIR=${STAGING_BINDIR_NATIVE}
	oe_runmake
	
}

do_install () {
        # Set a variable that the Makefiles obey for install.
        autotools_do_install
        install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d 
        install -m 0644 ${WORKDIR}/omniORB.cfg ${D}${sysconfdir}
        install -m 0755 ${WORKDIR}/omniNames ${D}${sysconfdir}/init.d/omniNames
        install -d ${D}${localstatedir}/omninames
}

BBCLASSEXTEND = "native"

NATIVE_INSTALL_WORKS = "1"
