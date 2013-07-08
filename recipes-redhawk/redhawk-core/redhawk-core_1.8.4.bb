DESCRIPTION = "REDHAWK Core Framework"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "omniorbpy omniorbpy-native log4cxx omniorb e2fsprogs apr-util apr zip expat boost boost-native python-numpy"
SRC_URI = "git://github.com/bagoulla/framework-core.git;branch=feature-no-xsd;protocol=git \
"
# I'm not sure how to set this to latest which is what I'd like.
SRCREV = "ff050137853a683ee6543e1ba45fddfb6c3742f0"


PR = "r5" 

S = "${WORKDIR}/git/src"

# We apparently have to inherit from pythonnative if we do stuff with the system python.
inherit autotools pkgconfig pythonnative

# Added so it picks up the xsd header files
#export LDFLAGS += "-L${STAGING_INCDIR}"

EXTRA_OECONF += "--with-boost=${STAGING_DIR_NATIVE}/usr --with-boost-libdir=${STAGING_DIR_NATIVE}/usr/lib --with-boost-filesystem=boost_filesystem --disable-java"

# Information about python is generally determined during the configure call but it will check the host system and not the build system (since it makes calls to the system python)
CACHED_CONFIGUREVARS += "PYTHON=${STAGING_DIR_NATIVE}/usr/bin/python-native/python BOOST_THREAD_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_FILESYSTEM_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_LDPATH=${STAGING_DIR_NATIVE}/usr/lib BOOST_SYSTEM_LDPATH=${STAGING_DIR_NATIVE}/usr/lib am_cv_python_pythondir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages am_cv_python_pyexecdir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages"

