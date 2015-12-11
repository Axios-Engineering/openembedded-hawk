
inherit redhawk-base

EXTRA_OECONF += "--libdir=${SDRROOT}/lib --includedir=${OSSIEHOME}/include"

do_configure_prepend() {
  # A bit of a hack but xmldir is one back from includedir which is automatically set
  sed -i 's,xmldir =.*,xmldir = $(includedir)/..,g' Makefile.am
}

