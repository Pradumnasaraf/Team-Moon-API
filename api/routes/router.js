const router = require("express").Router();

const getTodo = require("../controllers/getTodo");
const postTodo = require("../controllers/postTodo");
const updateTodo = require("../controllers/updateTodo");
const deleteTodo = require("../controllers/deleteTodo");

router.get("/get", getTodo);
router.post("/post", postTodo);
router.put("/update/:todoID", updateTodo);
router.delete("/delete/:todoID", deleteTodo);

module.exports = router;
