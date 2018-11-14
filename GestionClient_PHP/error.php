<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
       	<link rel="stylesheet" href="css/default.css">

        <title>GestionClient PHP - Clients</title>
    </head>
 
    <body>
 
    <?php include_once("includes/header.php"); ?>
    
    <p>Oups ... Une erreur est survenue !</p>
    
    <?php if(isset($_GET['msg'])) : ?>
    <p class="errorMessages"><?php echo htmlspecialchars($_GET['msg']); ?></p>
    <?php endif; ?>
    
    <?php include_once("includes/footer.php"); ?>
    
    </body>
</html>