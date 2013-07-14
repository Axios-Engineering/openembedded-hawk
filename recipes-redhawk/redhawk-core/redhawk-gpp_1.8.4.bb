DESCRIPTION = "REDHAWK Core Framework GPP"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-core"
SRC_URI = "git://github.com/RedhawkSDR/framework-GPP.git;branch=master;protocol=git \
"
# I'm not sure how to set this to latest which is what I'd like.
SRCREV = "cb0f2d53877708f4725d53cb0207fb9599094f0f"

PR = "r0" 

S = "${WORKDIR}/git/python"


# Set install location OSSIEHOME and SDRROOT

OSSIEHOME = "/usr/local/redhawk/core/"
SDRROOT = "/var/redhawk/sdr/"

FILES_${PN} += " \
${SDRROOT}dev/* \
"


# We apparently have to inherit from pythonnative if we do stuff with the system python.
inherit autotools pkgconfig pythonnative

# ADD to the ACLOCAL include path
EXTRA_AUTORECONF += "-I ${STAGING_DIR_TARGET}${OSSIEHOME}share/aclocal/ossie"

# Let it know where my OSSIEHOME is
EXTRA_OECONF += "--with-ossie=${STAGING_DIR_TARGET}${OSSIEHOME} --with-sdr=${SDRROOT}"

# Needed so that when the python distutils is run it can get the system prefix which, since it's the build system python will be /.../x86_64-linux/usr and replace it with our host systems name.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

# Information about python is generally determined during the configure call but it will check the host system and not the build system (since it makes calls to the system python)
CACHED_CONFIGUREVARS += "PYTHON=${STAGING_DIR_NATIVE}/usr/bin/python-native/python am_cv_python_pythondir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages am_cv_python_pyexecdir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages"

