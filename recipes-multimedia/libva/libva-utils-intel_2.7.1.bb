# Latest upgrades for media components depend upon libva-utils v2.7.1
# So need this upgrade

SUMMARY = "libva-utils is a collection of utilities from libva project"

DESCRIPTION = "libva-utils is a collection of utilities \
and examples to exercise VA-API in accordance with the libva \
project.VA-API is an open-source library and API specification, \
which provides access to graphics hardware acceleration capabilities \
for video processing. It consists of a main library and driver-specific \
acceleration backends for each supported hardware vendor"

HOMEPAGE = "https://01.org/linuxmedia/vaapi"
BUGTRACKER = "https://github.com/intel/libva-utils/issues"

SECTION = "x11"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=b148fc8adf19dc9aec17cf9cd29a9a5e"

SRC_URI = "git://github.com/intel/libva-utils.git;branch=v2.7-branch"
SRCREV = "5a24c635f6fb2b9ac31cab3360afca50e1860812"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

DEPENDS = "libva"

inherit meson pkgconfig ${COMPAT_DISTRO_FEATURE_CHECK}

# depends on libva which requires opengl
REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"
PACKAGECONFIG[x11] = "-Dx11=true, -Dx11=false,virtual/libx11 libxext libxfixes"
PACKAGECONFIG[wayland] = "-Dwayland=true, -Dwayland=false,wayland-native wayland"

PROVIDES = "libva-utils"
RPROVIDES_${PN} += "libva-utils"
