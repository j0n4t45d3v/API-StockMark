"use strict"
import express from 'express';
import {createServer} from 'http';
import { route } from "./router.js"
//import './database/dbconfig.js'
import "dotenv/config"

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(route);

const server = createServer(app);

const port = process.env.PORT || 8000;
const host = process.env.HOST || 'localhost';

server.listen(port, host, () => {
	console.log("Server Running...")
});
