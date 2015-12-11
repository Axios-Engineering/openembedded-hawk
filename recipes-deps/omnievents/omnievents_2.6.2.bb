DESCRIPTION = "OmniEvents enables CORBA applications to communicate through asynchronous broadcast channels"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=68ad62c64cc6c620126241fd429e68fe"
DEPENDS += "omniorb omniorb-native boost"

PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/RedhawkSDR/omniEvents.git;branch=develop;protocol=git \
file://01_omnievents_remove_runchecks.patch \
file://omniEvents \
"

SRCREV = "5c1850d806bb5412a30f422e394dad364a53a533"

inherit autotools-brokensep pkgconfig pythonnative update-rc.d

INITSCRIPT_NAME = "omniEvents"
INITSCRIPT_PARAMS = "defaults 99"

EXTRA_OECONF += "--with-omniorb=${STAGING_DIR_TARGET}/usr IDL=${STAGING_DIR_NATIVE}/usr/bin/omniidl --with-boost=${STAGING_DIR_TARGET}/usr --with-boost-system=boost_system --with-boost-thread=boost_thread"

# Cannot parallel the make call for omnievents, its busted.
PARALLEL_MAKE=""

do_install_append() {
    install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/omniEvents ${D}${sysconfdir}/init.d/omniEvents
}
