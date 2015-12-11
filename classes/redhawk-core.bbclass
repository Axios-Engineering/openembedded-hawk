
inherit redhawk-base

EXTRA_OECONF += "--with-ossie=${OSSIEHOME} --disable-java --with-expat=${STAGING_DIR_TARGET}/usr idldir=${STAGING_DIR_TARGET}/usr/share/idl/omniORB OMNICOS_IDLDIR=${STAGING_DIR_TARGET}/usr/share/idl/omniORB/COS --includedir=${OSSIEHOME}/include --libdir=${OSSIEHOME}/lib --datadir=${OSSIEHOME}/share --exec_prefix=${OSSIEHOME} --bindir=${OSSIEHOME}/bin"
