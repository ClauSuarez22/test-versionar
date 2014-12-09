
class VersionarController {

    def test() { 

    	withJGit() { rf ->
		    pull().call()
		    def f = new File(rf,"test.txt")
		    f.createNewFile()
		    // Relative path
		    add().addFilepattern(f.name).call()
		    if(!status().call().isClean()) {
		        commit().setMessage("some comment").call()
		    }
		    push().call()
		    pull().call()
		}
		render "hola"
    }
}
