import groovy.json.*

class VersionarController {

    def test() { 

    	def json = request.JSON;

    	withJGit() { rf ->
		    pull().call()
		    def f = new File(rf,"/configurations")
		    f.createNewFile();
		    f.write(new JsonBuilder(json).toPrettyString())
		    f.renameTo("seo_configuration_MCO.json")
		    
		    // Relative path
		    add().addFilepattern(f.name).call()
		    if(!status().call().isClean()) {
		        commit().setMessage("Versionando MCO").call()
		    }
		    push().call()
		    pull().call()
		}
		render "listo"
    }
}
