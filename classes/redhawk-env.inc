
# Set install location OSSIEHOME and SDRROOT
OSSIEHOME = "/usr/lib/redhawk/core"
SDRROOT = "/usr/lib/redhawk/sdr"

CACHED_CONFIGUREVARS += "ossie_cv_ossie_home=${OSSIEHOME} ac_cv_pymod_ossie=yes"
EXTRA_OECONF += "--with-sdr=${SDRROOT} OSSIEHOME=${OSSIEHOME} SDRROOT=${SDRROOT} --with-boost=${STAGING_DIR_TARGET}/usr --with-boost-system=boost_system --with-boost-thread=boost_thread --with-boost-filesystem=boost_filesystem --with-boost-regex=boost_regex --disable-log4cxx"

export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${OSSIEHOME}/lib/pkgconfig"
export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${OSSIEHOME}/lib64/pkgconfig"

