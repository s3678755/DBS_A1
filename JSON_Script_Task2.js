const fs = require('fs');

let rawdata = fs.readFileSync('./output.json');
const sample = JSON.parse(rawdata);

const map = new Map();
const mapName = new Map();
sample.map(sensor => {
       if (map.has(sensor.Sensor_ID)) {
              const currentDetail = map.get(sensor.Sensor_ID);
              map.set(
                sensor.Sensor_ID, [...currentDetail, {
                  Date_Time: sensor.Date_Time, 
                  Year: sensor.Year, 
                  Month: sensor.Month, 
                  Mdate: sensor.Mdate, 
                  Day: sensor.Day, 
                  Time: sensor.Time, 
                  Hourly_Counts: sensor.Hourly_Counts 
                }]);
       } else {
              mapName.set(sensor.Sensor_ID, sensor.Sensor_Name);
              map.set(
                sensor.Sensor_ID, [{
                  Date_Time: sensor.Date_Time, 
                  Year: sensor.Year, 
                  Month: sensor.Month, 
                  Mdate: sensor.Mdate, 
                  Day: sensor.Day, 
                  Time: sensor.Time,
                  Hourly_Counts: sensor.Hourly_Counts }]);
       }
});


const result = [];
Array.from(map.keys()).forEach(key => {
       result.push({ 
         Sensor_ID: key, 
         Sensor_Name: mapName.get(key), 
         Details: map.get(key)
       })
});

console.log(result);
let data = JSON.stringify(result, null, 2);
fs.writeFileSync('output-mongo.json', data);