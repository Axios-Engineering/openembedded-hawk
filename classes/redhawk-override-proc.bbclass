
# The processor is hard coded into the spd of the components
# which makes it a pain to build and run for different architectures
# this class makes an attempt to find and set the proc to the
# value provided by the user in REDHAWK_PROCESSOR_NAME

# Its a total hack and I need a better solution.
do_compile_append() {

  # Only do this if they have defined the processor name variable
  if [ "${REDHAWK_PROCESSOR_NAME}" ]; then

    # Find all of the spd files this package installed
    for file in $(find ${WORKDIR} -iname "*.spd.xml"); do

        # Dont do anything if the processor is already called out in the file
        if ! grep -q "${REDHAWK_PROCESSOR_NAME}" $file; then

          # Replace the x86_64 implementation with the desired one
          sed -i 's,<processor name="x86_64"/>,<processor name="${REDHAWK_PROCESSOR_NAME}"/>,g' $file
        fi
    done

    # One more horrible hack, the Device managers prf for the now replaced x86_64 needs to be adjusted with a new value
    for file in $(find ${WORKDIR} -iname "DeviceManager.Linux.x86_64.prf.xml"); do

        # Dont do anything if the processor is already called out in the file
        if ! grep -q "${REDHAWK_PROCESSOR_NAME}" $file; then

          # Replace the x86_64 implementation with the desired one
          sed -i 's,<value>x86_64</value>,<value>${REDHAWK_PROCESSOR_NAME}</value>,g' $file
        fi
    done
  fi
}
