FROM openliberty/open-liberty:kernel-java11-openj9-ubi

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/

# Use configure.sh to pre-warm Open Liberty and have faster stat time of the image (but slower build time)
# RUN configure.sh
