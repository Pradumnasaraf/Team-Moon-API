const router = require("express").Router();

const getTodos = require("../controllers/getTodos");
const postTodo = require("../controllers/postTodo");
const updateTodo = require("../controllers/updateTodo");
const deleteTodo = require("../controllers/deleteTodo");
const getTodo = require("../controllers/getTodo");

router.get("/todos", getTodos);
router.get("/todo/:todoID", getTodo);
router.post("/create", postTodo);
router.put("/update/:todoID", updateTodo);
router.delete("/delete/:todoID", deleteTodo);

module.exports = router;
