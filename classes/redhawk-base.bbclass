
# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the sasme as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative

# This file sets OSSIEHOME and other environment variables used by autotools
include redhawk-env.inc

# Yocto no longer uses -fpermissive by default however the framework needs it for xsd 3.3
CXXFLAGS_append = " -fpermissive "

# Needed so that when the python distutils is run it can get the system prefix which, since it's the build system python will be /.../x86_64-linux/usr and replace it with our host systems name.
do_configure_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}

  # prefix is overused term, I think they mean SDR_ROOT
  sed -i 's/xmldir = $(prefix)/xmldir = $(SDR_ROOT)/g' Makefile.am
  sed -i 's/bindir = $(prefix)/bindir = $(SDR_ROOT)/g' Makefile.am
  sed -i 's/domdir = $(prefix)/domdir = $(SDR_ROOT)/g' Makefile.am
  sed -i 's,${prefix}/dom/deps,${SDR_ROOT}/dom/deps,g' configure.ac

  # Makefiles have started adding the OSSIEHOME m4 files to the aclocal path but it doesnt work quite right with out setup 
  sed -i '/ACLOCAL_AMFLAGS =/d' Makefile.am

  # These files are required and we generally dont have them
  touch ./NEWS ./README ./AUTHORS ./ChangeLog
}

# Needed so that when the python distutils is run it can get the system prefix.
do_install_prepend() {
  export BUILD_SYS=${BUILD_SYS}
  export HOST_SYS=${HOST_SYS}
  export STAGING_INCDIR=${STAGING_INCDIR}
  export STAGING_LIBDIR=${STAGING_LIBDIR}
}

