DESCRIPTION = "OmniORB High Performance ORB"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=75b02c2872421380bbd47781d2bd75d3"
DEPENDS += "omniorb-native python"
DEPENDS_virtclass-native += "python-native"
PR = "r2"

SRC_URI = "http://downloads.sourceforge.net/omniorb/omniORB-4.1.4.tar.gz;name=omniORB414targz \
file://omniorb_4.1.4.patch \ 
file://omniORB.cfg \
file://omniORB-cross.patch \
file://omniORB_embedded_appl.patch \
file://rm_LongDouble.patch \
file://pyPrefixIsPrefix.patch \
file://fixPythonShebang.patch \
file://omniNames \
"
SRC_URI_virtclass-native = "http://downloads.sourceforge.net/omniorb/omniORB-4.1.4.tar.gz;name=omniORB414targz \
	  file://omniorb_4.1.4.patch \
"


SRC_URI[omniORB414targz.md5sum] = "1f6070ff9b6339876976d61981eeaa6a"
SRC_URI[omniORB414targz.sha256sum] = "84fb9790c25d6e46248c9773747e393b429573190da2150850d4a49debda4e8e"

S = "${WORKDIR}/omniORB-${PV}"

# Here we need python libraries and the softlink for the omniidlmodule, we have to disable the check for soft links.
# Alternativly, we could packge this into the dev package and then pull that in but that would also get all the headers
# and idl files
INSANE_SKIP_${PN} += "dev-so"
FILES_${PN} += "${libdir}/python*/*"
FILES_${PN}-dev += "${datadir}/idl/omniORB/* ${datadir}/idl/omniORB/cos/*"
FILES_${PN}-dbg += "${libdir}/python*/site-packages/.debug/*"

TARGET_CC_ARCH += "${LDFLAGS}"

inherit autotools pkgconfig pythonnative update-rc.d

INITSCRIPT_NAME = "omniNames"
INITSCRIPT_PARAMS = "defaults 10"

do_compile () {
        export TOOLBINDIR=${STAGING_BINDIR_NATIVE}
	oe_runmake
	
}

do_compile_virtclass-native() {
        oe_runmake
}

do_install () {
        # Set a variable that the Makefiles obey for install.
        autotools_do_install
        install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d 
        install -m 0644 ${WORKDIR}/omniORB.cfg ${D}${sysconfdir}
        install -m 0755 ${WORKDIR}/omniNames ${D}${sysconfdir}/init.d/omniNames
        install -d ${D}${localstatedir}/omninames
	
	#only executable libraries are stripped by the stripper
	chmod +x ${WORKDIR}/image/usr/lib/lib*
}

do_install_virtclass-native() {
        autotools_do_install

	# Ugly hack so libtool does not find native libs when building cross
	# packages We really only build this package for omniidl anyway
        rm -f  ${D}${libdir}/libomni*
}

BBCLASSEXTEND = "native"

NATIVE_INSTALL_WORKS = "1"
