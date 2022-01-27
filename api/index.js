const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const dotenv = require("dotenv");

const router = require("./routes/router");

dotenv.config();

const app = express();

mongoose
  .connect(process.env.MONGODB_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => {
    console.log("Connected to MongoDB");
  })
  .catch((err) => {
    console.log(err);
  });

app.get("/", (req, res) => {
  res.send("Team Moon API 🚀🌑");
});

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(router);

const port = process.env.PORT || 3000;

app.listen(port, () => {
  console.log("Team Moon API is going to the moon 🚀🌑");
});

module.exports = app;
