//Output sensor id and name
const fs = require('fs');

let rawdata = fs.readFileSync('./output.json');
const sample = JSON.parse(rawdata);

const map = new Map();
sample.map(sensor => {
       if (!map.has(sensor.Sensor_ID)) {
              map.set(
                sensor.Sensor_ID, sensor.Sensor_Name);
       }
});

const result = [];
Array.from(map.keys()).forEach(key => {
       result.push({ 
         Sensor_ID: key, 
         Sensor_Name: map.get(key)
       })
});

console.log(result);
let data = JSON.stringify(result, null, 2);
fs.writeFileSync('output-derby-sensor.json', data);