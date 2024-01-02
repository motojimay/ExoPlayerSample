const express = require('express');
const fs = require('fs');
const app = express();

function getVideoData() {
    let objects = [];
    const sampleDirectoryPath = `${__dirname}/video/sample`;
    const files = fs.readdirSync(sampleDirectoryPath);
    let key = 1;
    for (const file of files) {
        let obj = {
            "key": key.toString(),
            "video_name_with_format": file,
            "video_name": file.replace(".mp4", ""),
            "file_full_path": `${__dirname}/video/sample/${file}`
        }
        objects.push(obj);
        key++
    }
    return objects
}

app.get('/videos', (req, res) => {
    let body = {
        "result": "0",
        "data": getVideoData()
    }
    res.header('Content-Type', 'application/json; charset=utf-8');
    res.status(200).send(body);
});

app.get('/video/key/:key', (req, res) => {
    let key = req.params.key;
    let videos = getVideoData();

    for (const video of videos) {
        if (video.key === key) {
            let body = {
                "result": "0",
                "data": video
            }
            res.header('Content-Type', 'application/json; charset=utf-8');
            res.status(200).send(body);
            return
        }
    }
    const body = { 
        "result": "1",
        "message": "Not Found" 
    };
    res.header('Content-Type', 'application/json; charset=utf-8');     
    res.status(400).send(body);
});

app.get('/video/name/:name', (req, res) => {
    let videoName = req.params.name;
    let videos = getVideoData();

    for (const video of videos) {
        if (video.video_name === videoName) {
            let body = {
                "result": "0",
                "data": video
            }
            res.header('Content-Type', 'application/json; charset=utf-8');
            res.status(200).send(body);
            return
        }
    }
    const body = { 
        "result": "1",
        "message": "Not Found" 
    };
    res.header('Content-Type', 'application/json; charset=utf-8');     
    res.status(400).send(body);
});

app.get('/video/sample/:name/', (req, res) => {

    const pathParamValue = `${req.params.name}.mp4`;
    const videoPath = `${__dirname}/video/sample/${pathParamValue}`;
    const stat = fs.statSync(videoPath);
    const fileSize = stat.size;
    const range = req.headers.range;

    if (range) {
        const parts = range.replace(/bytes=/, '').split('-');
        const start = parseInt(parts[0], 10);
        const end = parts[1] ? parseInt(parts[1], 10) : fileSize - 1;
        const chunkSize = end - start + 1;
        const file = fs.createReadStream(videoPath, { start, end });
        const head = {
          'Content-Range': `bytes ${start}-${end}/${fileSize}`,
          'Accept-Ranges': 'bytes',
          'Content-Length': chunkSize,
          'Content-Type': 'video/mp4',
        };
        res.writeHead(206, head);
        file.pipe(res);
    } else {
        const head = {
          'Content-Length': fileSize,
          'Content-Type': 'video/mp4',
        };
        res.writeHead(200, head);
        fs.createReadStream(videoPath).pipe(res);
    }
});

app.use('/', (req, res) => {
    const body = { 
        "result": "1",
        "message": "Invalid Request" 
    };
    res.header('Content-Type', 'application/json; charset=utf-8');     
    res.status(400).send(body);     
}) 

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
