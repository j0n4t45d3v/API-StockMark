'use strict'

async function post(req, res) {
	console.log("Tarefa criada");
	return res.status(201);
}

export { post }
