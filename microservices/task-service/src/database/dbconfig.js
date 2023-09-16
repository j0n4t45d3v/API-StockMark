'use strict'
import { connect } from "mongoose";

async function initConnectionDb() {
	await connect("mongodb://localhost:27017/database")
		.then(() => console.log("Banco conectado"));
}

initConnectionDb().catch(err => console.error(err));
