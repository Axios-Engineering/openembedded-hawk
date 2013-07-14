DESCRIPTION = "REDHAWK Framework BulkIO Interfaces"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "redhawk-core"
SRC_URI = "git://github.com/RedhawkSDR/framework-bulkioInterfaces.git;branch=master;protocol=git \
"
# I'm not sure how to set this to latest which is what I'd like.
SRCREV = "1079f7698af678472fb636563129831ffc5c8346"

PR = "r0" 

S = "${WORKDIR}/git"


# Set install location OSSIEHOME and SDRROOT

OSSIEHOME = "/usr/local/redhawk/core/"
SDRROOT = "/var/redhawk/sdr/"

FILES_${PN} += " \
${OSSIEHOME}/* \
${STAGING_DIR_TARGET}${OSSIEHOME}/* \
"

# I have no idea what this debug hidden directory is used for but it throws an error if not part of a debug package and I don't want to package it alone so we'll skip the check.
INSANE_SKIP_${PN} += "debug-files"

# We've got a couple static dev files and sym links we need and I don't want to worry about calling them out in their own package.
INSANE_SKIP_${PN} += "staticdev"
INSANE_SKIP_${PN} += "dev-so"

# We apparently have to inherit from pythonnative if we do stuff with the system python.
inherit autotools pkgconfig pythonnative

# ADD to the ACLOCAL include path
EXTRA_AUTORECONF += "-I ${STAGING_DIR_TARGET}${OSSIEHOME}share/aclocal/ossie"

# with-ossie tells it where to look for the current ossiehome for dependencies, the prefix junk is where it will install stuff.
EXTRA_OECONF += "--with-ossie=${STAGING_DIR_TARGET}${OSSIEHOME} --with-sdr=${SDRROOT} --exec_prefix=${OSSIEHOME} --prefix=${OSSIEHOME} --bindir=${OSSIEHOME}/bin --datadir=${OSSIEHOME}/share --libdir=${OSSIEHOME}/lib"

# Needed so that when the python distutils is run it can get the system prefix which, since it's the build system python will be /.../x86_64-linux/usr and replace it with our host systems name.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

# Information about python is generally determined during the configure call but it will check the host system and not the build system (since it makes calls to the system python)
CACHED_CONFIGUREVARS += "PYTHON=${STAGING_DIR_NATIVE}/usr/bin/python-native/python am_cv_python_pythondir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages am_cv_python_pyexecdir=${STAGING_DIR_NATIVE}/usr/lib/python2.7/site-packages"

