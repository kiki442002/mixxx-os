# Remove glibc-specific locale recommendation when building with musl libc
# locale-base-c is provided by glibc-locale packages which don't exist with musl
RRECOMMENDS:${PN}:remove:libc-musl = "locale-base-c"
