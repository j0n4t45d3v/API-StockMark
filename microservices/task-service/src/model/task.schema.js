'use strict'
import mongoose, { Schema } from "mongoose";

const checklistSchema = new Schema({ name: [String] });
const assingShema = new Schema({id: String});

const taskSchema = new Schema({
	title: String,
	description: String,
	checklists: [checklistSchema],
	assing: [assingShema]
});

const Task = mongoose.model("Task", taskSchema);

export { Task }
