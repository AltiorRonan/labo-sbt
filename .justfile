import 'home.just'

_: default

# Run Version A.
[group('Labo SBT')]
run-va:
    just clean-all
    sbt "VA/runMain fr.altior.labo.va.MainVa"

# Publish Version A.
[group('Labo SBT')]
va:
    #!/usr/bin/env bash
    SBT_NATIVE_CLIENT=true
    just clean-all

    #echo -e "{{ GREEN }} Package... {{ RESET }}"
    #sbt "VA/package"

    echo -e "{{ GREEN }} Publish local... {{ RESET }}"
    sbt "VA/publishLocal"

    sbt shutdown
    just sbt-list

# Run Version B.
[group('Labo SBT')]
run-vb:
    just clean-all
    sbt "VB/runMain fr.altior.vb.MainVb"

# Publish Version B.
[group('Labo SBT')]
vb:
    #!/usr/bin/env bash
    SBT_NATIVE_CLIENT=true
    just clean-all

    echo -e "{{ GREEN }} Publish local... {{ RESET }}"
    sbt "VB/publishLocal"

    sbt shutdown
    just sbt-list
