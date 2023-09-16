
'use strict'
import { Router } from "express";
import { post } from "./controller/task.controller.js";

export const route = Router();

route.get("/tasks", (req, res) => res.send({message: "pegando!"}));

route.post("/tasks/create", post);
