# MVP

Hola

Instructions:
- It is a springboot application, so use IDE like STS for easier use, wither you can directly run the test case or run the 'spring boot application' and use the shell script to invoke the below curls,
- Minimal test cases are provided, but feel free to refer below curl to explore more.

Assumptions:
- wget is installed and make sure it points to proper location(refer properties and contstants file),
- file names are unique for the demo purpose,
- At any given time, one batch is being processed,
- it is implemented minimalist possible way and not making as complex solution

Test commands:

curl -X POST \
  http://localhost:8080/download/files \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 7dee4cd7-6721-5964-812d-407ce4d3b439' \
  -d '{"path": ["http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_10mb.mp4",
				"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_20mb.mp4",
				"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/360/big_buck_bunny_360p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4",
				"http://www.sample-videos.com/video/3gp/240/big_buck_bunny_240p_30mb.3gp",
				"http://www.sample-videos.com/video/mkv/240/big_buck_bunny_240p_30mb.mkv"]}'


curl -X GET \
  http://localhost:8080/download/status \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a38a0f92-44a0-f1ed-daa2-790b52f1f4a0'

curl -X GET \
  http://localhost:8080/download/big_buck_bunny_720p_10mb.mp4/status \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 28360636-5bb2-a58b-b207-dd9666e47345'
