DESCRIPTION = "Python bindings for omniORB"
SECTION =  "devel"
PRIORITY = "optional"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=dcf3c825659e82539645da41a7908589"
DEPENDS += "omniorb omniorbpy-native python"
DEPENDS_virtclass-native += "omniorb-native python-native"

# Apparently this will allow this file to create symlinks to so files without throwing an error
INSANE_SKIP_${PN} = "dev-so"

PR = "r4"

SRC_URI = "http://downloads.sourceforge.net/omniorb/omniORBpy-3.4.tar.gz;name=omniORBpy34targz \
file://omniORBpy-cross.patch \
file://omniORBpy_3.4_modules_codesets_dir.mk.patch \
file://omniORBpy_3.4_modules_connections_dir.mk.patch \
file://omniORBpy_3.4_modules_dir.mk.patch \
file://omniORBpy_3.4_modules_sslTP_dir.mk.patch \
"
SRC_URI_virtclass-native = "http://downloads.sourceforge.net/omniorb/omniORBpy-3.4.tar.gz;name=omniORBpy34targz"

SRC_URI[omniORBpy34targz.md5sum] = "d054e99c89c20f86927c52010e9e7449"
SRC_URI[omniORBpy34targz.sha256sum] = "c786676f5d76082bbe6a4031aed1a08b45345d75c0b5aacc4cdc26b69fb9c782"

S = "${WORKDIR}/omniORBpy-${PV}"

EXTRA_OECONF = "--with-omniorb=${STAGING_DIR_TARGET}/usr"
EXTRA_OECONF_virtclass-native = "--with-omniorb=${STAGING_DIR_NATIVE}/usr"


FILES_${PN} += " \
${libdir}/python2.7/site-packages/*.pth \
${libdir}/python2.7/site-packages/*.pyo \
${libdir}/python2.7/site-packages/*.py \
${libdir}/python2.7/site-packages/*.pyc \
${libdir}/python2.7/site-packages/*.so.* \
${libdir}/python2.7/site-packages/*.so \
${libdir}/python2.7/site-packages/omniidl_be/* \
${libdir}/python2.7/site-packages/omniORB/* \
${libdir}/python2.7/site-packages/omniORB/COS/* \
${libdir}/python2.7/site-packages/omniORB/COS/*/* \
${libdir}/python2.7/site-packages/CosNaming__POA/* \
${libdir}/python2.7/site-packages/CosNaming/* \
"

inherit autotools pkgconfig distutils-base

#ERROR: QA Issue with omniorbpy-dev: No GNU_HASH in the elf binary...
#See for more info: http://old.nabble.com/No-GNU_HASH-found-in-elf-binary-td23072960.html
TARGET_CC_ARCH += "${LDFLAGS}"

do_compile () {
	export OMNIORB_BINDIR=${STAGING_BINDIR_NATIVE}
    oe_runmake
}

do_compile_virtclass-native () {
    oe_runmake
}

# omniOrb already provides the init file
do_install_append() {
  rm -f ${D}${libdir}/python*/site-packages/omniidl_be/__init__.*
}

BBCLASSEXTEND = "native"

NATIVE_INSTALL_WORKS = "1"
