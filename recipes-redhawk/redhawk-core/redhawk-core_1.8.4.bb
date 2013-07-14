DESCRIPTION = "REDHAWK Core Framework"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "omniorbpy omniorbpy-native log4cxx omniorb e2fsprogs apr-util apr zip expat boost boost-native python-numpy python-threading"
SRC_URI = "git://github.com/bagoulla/framework-core.git;branch=feature-no-xsd;protocol=git \
file://01_OSSIEHOME_prefix.patch \
"
# I'm not sure how to set this to latest which is what I'd like.
SRCREV = "2204f010fe2c133b2ddc4cfaa38a75cd16ee8b54"

PR = "r0" 

S = "${WORKDIR}/git/src"


# Set install location OSSIEHOME and SDRROOT

OSSIEHOME = "/usr/local/redhawk/core/"
SDRROOT = "/var/redhawk/sdr/"

FILES_${PN} += " \
${OSSIEHOME}/* \
${STAGING_DIR_TARGET}${OSSIEHOME}/* \
${SDRROOT}/* \
${STAGING_DIR_TARGET}${SDRROOT}/* \
${libdir}/python2.7/site-packages/ossie/* \
${STAGING_DIR_NATIVE}${libdir}/python2.7/site-packages/ossie/* \
${libdir}/python2.7/site-packages/ossie* \
${STAGING_DIR_NATIVE}${libdir}/python2.7/site-packages/ossie* \
${libdir}/python2.7/site-packages/redhawk/* \
${STAGING_DIR_NATIVE}${libdir}/python2.7/site-packages/redhawk/* \
${datadir}/xml/* \
${STAGING_DIR_TARGET}${datadir}/xml/* \
"

# I have no idea what this debug hidden directory is used for but it throws an error if not part of a debug package and I don't want to package it alone so we'll skip the check.
INSANE_SKIP_${PN} += "debug-files"

# We've got a couple static dev files and sym links we need and I don't want to worry about calling them out in their own package.
INSANE_SKIP_${PN} += "staticdev"
INSANE_SKIP_${PN} += "dev-so"


# We apparently have to inherit from pythonnative if we do stuff with the system python.
inherit autotools pkgconfig pythonnative

# Added so it picks up the xsd header files
#export LDFLAGS += "-L${STAGING_INCDIR}"

EXTRA_OECONF += "--with-boost=${STAGING_DIR_TARGET}/usr --with-boost-system=boost_system --with-boost-filesystem=boost_filesystem --with-boost-thread=boost_thread --with-boost-regex=boost_regex --disable-java --with-ossie=${OSSIEHOME} --with-sdr=${SDRROOT} --exec_prefix=${OSSIEHOME} --prefix=${OSSIEHOME} --bindir=${OSSIEHOME}/bin --datadir=${OSSIEHOME}/share --libdir=${OSSIEHOME}/lib --disable-log4cxx"

# Needed so that when the python distutils is run it can get the system prefix which, since it's the build system python will be /.../x86_64-linux/usr and replace it with our host systems name.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}

  # Copy the acinclude folder to the aclocal arm sysroot so that we can build the GPP and BulkioInterfaces libraries later
#  mkdir -p ${STAGING_DIR_TARGET}${OSSIEHOME}share/aclocal/ossie
#  cp ${S}/acinclude/* ${STAGING_DIR_TARGET}${OSSIEHOME}share/aclocal/ossie
}

# I have no idea what I'm doing.  All I know is I want a copy of the install in the STAGING_DIR_TARGET and I want the python modueles in my STAGING_DIR_NATIVE
do_install_append() {
  cp -r ${D}/* ${STAGING_DIR_TARGET}
  cp -r ${D}${OSSIEHOME}/lib/python/* ${STAGING_DIR_NATIVE}${libdir}/python2.7/site-packages/
}

# Information about python is generally determined during the configure call but it will check the host system and not the build system (since it makes calls to the system python)
CACHED_CONFIGUREVARS += "PYTHON=${STAGING_DIR_NATIVE}/usr/bin/python-native/python BOOST_THREAD_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_FILESYSTEM_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_SYSTEM_LDPATH=${STAGING_DIR_NATIVE}/usr/lib am_cv_python_pythondir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages am_cv_python_pyexecdir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages"

