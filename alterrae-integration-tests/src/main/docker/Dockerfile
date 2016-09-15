FROM mongo

COPY data /data
COPY import.sh /import.sh
RUN chmod +x /import.sh

CMD ["/import.sh"]