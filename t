https://linuxdicasesuporte.blogspot.com/2022/02/equalizador-para-pulseaudio-na-sua.html


ffmpeg -i rtsp://teste:TItaniwm2023@192.168.100.151:554/ISAPI/Streaming/channels/101 -c copy -f hls -hls_time 10 -hls_list_size 6 -hls_flags delete_segments -hls_base_url http://localhost:8751/ camera1.m3u8
