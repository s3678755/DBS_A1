// import sampleTest from './sample-output.json';

// const sample1 = sampleTest;
const fs = require('fs');
let rawdata = fs.readFileSync('./output.json');
const sample = JSON.parse(rawdata);
// const sample =
// [
//     {
//         "ID": "2887628",
//         "Date_Time": "11\/01\/2019 05:00:00 PM",
//         "Year": "2019",
//         "Month": "November",
//         "Mdate": "1",
//         "Day": "Friday",
//         "Time": "17",
//         "Sensor_ID": "34",
//         "Sensor_Name": "Flinders St-Spark La",
//         "Hourly_Counts": "300"
//     },
//     {
//         "ID": "2887629",
//         "Date_Time": "12\/01\/2019 05:00:00 PM",
//         "Year": "2019",
//         "Month": "December",
//         "Mdate": "1",
//         "Day": "Friday",
//         "Time": "17",
//         "Sensor_ID": "34",
//         "Sensor_Name": "Flinders St-Spark La",
//         "Hourly_Counts": "604"
//     }
// ]

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
                  ID: sensor.ID, 
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
                  ID: sensor.ID, 
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
fs.writeFileSync('student-2.json', data);