import groovy.json.JsonBuilder

class VersionarController {

    def test() { 

    	def json = request.JSON;

    	withJGit() { rf ->
    		println "pathh: " + rf.path
		    pull().call()
		    def f = new File(rf,"test.txt")
		    f.createNewFile()
		    // Relative path
		    add().addFilepattern(f.name).call()
		    if(!status().call().isClean()) {
		        commit().setMessage("some comment").call();
                
		    }
            
		    push().call()
		    pull().call()
		}
		render "listo"
    }
}
